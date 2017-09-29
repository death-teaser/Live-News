package com.example.root.androidrssfeed;

import java.io.Serializable;

/**
 * Created by root on 29/9/17.
 */

public class NewsItem implements Serializable {
        String title;
        String image;
        String link;
        String info;

        public void setImage(String image) {
            this.image = image;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getImage() {
            return image;
        }

        public String getInfo() {
            return info;
        }

        @Override
        public String toString() {
            return title;
        }


}
