package com.wix.mediaplatform.image;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ImageTest {

    @Test
    public void crop() throws Exception {
        String url = new Image("//test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1,q_75,usm_0.5_0.2_0.0/file.png")
                .crop(100, 100, 1, 1, 1)
                .brightness(10)
                .contrast(10)
                .hue(10)
                .saturation(10)
                .blur(10)
                .unsharpMask(10, 10, 10)
                .jpeg(10)
                .toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/crop/w_100,h_100,x_1,y_1,scl_1.0,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName"));
    }

    @Test
    public void cropWithMetadata() throws Exception {
        String url = new Image("//test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1,q_75,usm_0.5_0.2_0.0/file.png#w_1000,h_2000,mt_image%2Fpng")
                .crop(100, 100, 1, 1, 1)
                .brightness(10)
                .contrast(10)
                .hue(10)
                .saturation(10)
                .blur(10)
                .unsharpMask(10, 10, 10)
                .jpeg(10)
                .toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/crop/w_100,h_100,x_1,y_1,scl_1.0,bl,blur_10,br_10,con_10,eye,hue_10,neg,oil,pix_10,pixfs_10,q_10,sat_10,shrp_0.1,usm_10_10_10/fileName#w_500,h_500,mt_image/jpeg"));
    }

    @Test
    public void acceptsHTTP() throws Exception {
        String url = new Image("http://test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1/file.png#w_1000,h_2000,mt_image%2Fpng").toUrl();

        assertThat(url, is("http://domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void acceptsHTTPS() throws Exception {
        String url = new Image("https://test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1/file.png#w_1000,h_2000,mt_image%2Fpng").toUrl();

        assertThat(url, is("https://domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }

    @Test
    public void acceptsDoubleSlash() throws Exception {
        String url = new Image("//test.com/1111/images/324234/v1/crop/w_709,h_400,x_1,y_2,scl_1/file.png#w_1000,h_2000,mt_image%2Fpng").toUrl();

        assertThat(url, is("//domain.com/user/bucket/fileId/v1/fill/w_100,h_100/fileName"));
    }
}