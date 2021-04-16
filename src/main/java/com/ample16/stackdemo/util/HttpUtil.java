package com.ample16.stackdemo.util;

import com.kuaidi100.exception.BusinessException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zefeng_lin on 2021/04/14.
 */
public class HttpUtil {
    private static final int tryTimes = 3;

    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;
    private boolean noSsl;

    private HttpUtil(boolean flag, String certPath, String certPassword) {
        if (flag) {
            KeyStore keyStore = null;
            SSLContext sslcontext = null;
            try {
                keyStore = KeyStore.getInstance("PKCS12");
                FileInputStream inputStream = new FileInputStream(new File(certPath));
                keyStore.load(inputStream, certPassword.toCharArray());
                sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, certPassword.toCharArray()).build();
            } catch (Exception e) {
                throw new BusinessException("证书加载异常", e);
            }
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(30000).setConnectionRequestTimeout(30000).build();
            noSsl = false;
        } else {
            httpClient = HttpClients.createDefault();
            noSsl = true;
        }
    }

    public static HttpUtil defaultHttp() {
        return new HttpUtil(false, null, null);
    }

    public static HttpUtil sslHttp(String certPath, String certPassword) {
        return new HttpUtil(true, certPath, certPassword);
    }

    public String get(String url) {
        return this.get(url, tryTimes);
    }

    public byte[] download(String url) {
        return this.download(url, tryTimes);
    }

    public byte[] postDownload(String url, String content) {
        return this.postDownload(url, content, tryTimes);
    }

    public byte[] postDownload(String url, String content, Integer count) {
        try {
            URL urlPost = new URL(url);
            URI uri = new URI(urlPost.getProtocol(), urlPost.getHost() + ":" + urlPost.getPort(), urlPost.getPath(), urlPost.getQuery(), null);
            HttpPost httpPost = new HttpPost(uri);
            if (!noSsl) {
                httpPost.setConfig(requestConfig);
            }
            if (content != null) {
                StringEntity entity = new StringEntity(content, Consts.UTF_8);
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity);
            return bytes;
        } catch (Exception e) {
            if (count > 0) {
                count--;
                return this.postDownload(url, content, count);
            }
            throw new BusinessException("http post请求异常,data=" + content, e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ioExp) {
                    throw new BusinessException("http post请求异常,data=" + content, ioExp);
                }
            }
        }
    }


    public String get(String url, Integer count) {
        try {
            URL urlGet = new URL(url);
            URI uri = new URI(urlGet.getProtocol(), urlGet.getHost(), urlGet.getPath(), urlGet.getQuery(), null);
            HttpGet httpGet = new HttpGet(uri);
            if (!noSsl) {
                httpGet.setConfig(requestConfig);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseContent = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
            return responseContent;
        } catch (Exception e) {
            if (count > 0) {
                count--;
                return this.get(url, count);
            }
            throw new BusinessException("http get请求异常,url=" + url, e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ioExp) {
                    throw new BusinessException("http get请求异常,url=" + url, ioExp);
                }
            }
        }
    }

    public byte[] download(String url, Integer count) {
        try {
            URL urlGet = new URL(url);
            URI uri = new URI(urlGet.getProtocol(), urlGet.getHost(), urlGet.getPath(), urlGet.getQuery(), null);
            HttpGet httpGet = new HttpGet(uri);
            if (!noSsl) {
                httpGet.setConfig(requestConfig);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity);
            return bytes;
        } catch (Exception e) {
            if (count > 0) {
                count--;
                return this.download(url, count);
            }
            throw new BusinessException("http get请求异常,url=" + url, e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ioExp) {
                    throw new BusinessException("http get请求异常,url=" + url, ioExp);
                }
            }
        }
    }

    public String post(String url, Map<String, String> params) {
        return post(url, params, tryTimes);
    }

    public String post(String url, String content) {
        return post(url, content, ContentType.TEXT_PLAIN.toString(), tryTimes);
    }

    public String post(String url, String content, String contentType) {
        return this.post(url, content, contentType, tryTimes);
    }

    /**
     * @param url
     * @param content json字符串
     * @return
     */
    public String post(String url, String content, String contentType, Integer count) {
        try {
            URL urlPost = new URL(url);
            URI uri = new URI(urlPost.getProtocol(), urlPost.getHost() + ":" + urlPost.getPort(), urlPost.getPath(), urlPost.getQuery(), null);
            HttpPost httpPost = new HttpPost(uri);
            if (!noSsl) {
                httpPost.setConfig(requestConfig);
            }
            if (content != null) {
                StringEntity entity = new StringEntity(content, Consts.UTF_8);
                httpPost.setEntity(entity);
            }
            httpPost.addHeader("Content-type", contentType);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseContent = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
            return responseContent;
        } catch (Exception e) {
            if (count > 0) {
                count--;
                return this.post(url, content, contentType, count);
            }
            throw new BusinessException("http post请求异常,data=" + content, e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ioExp) {
                    throw new BusinessException("http post请求异常,data=" + content, ioExp);
                }
            }
        }
    }

    public String post(String url, Map<String, String> params, Integer count) {
        try {
            URL urlPost = new URL(url);
            URI uri = new URI(urlPost.getProtocol(), urlPost.getHost() + ":" + urlPost.getPort(), urlPost.getPath(), urlPost.getQuery(), null);
            HttpPost httpPost = new HttpPost(uri);
            if (!noSsl) {
                httpPost.setConfig(requestConfig);
            }
            ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();//用于存放表单数据.
            //遍历map 将其中的数据转化为表单数据
            for (Map.Entry<String, String> entry :
                    params.entrySet()) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);
            httpPost.setEntity(urlEncodedFormEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseContent = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
            return responseContent;
        } catch (Exception e) {
            if (count > 0) {
                count--;
                return this.post(url, params, count);
            }
            throw new BusinessException("http post请求异常,data=" + params.toString(), e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ioExp) {
                    throw new BusinessException("http post请求异常,data=" + params.toString(), ioExp);
                }
            }
        }
    }

}
