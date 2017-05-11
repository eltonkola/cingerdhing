package com.eltonkola.crud.domain;
import com.eltonkola.crud.utils.Utils;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "news_article")
public class NewsArticle {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 100000 )
    @Lob
    private String content;

    public NewsArticle() {}

    public NewsArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format(
                "NewsArticle[id=%d, title='%s', content='%s']",
                id, title, content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle(){
        if(Utils.getWordsCount(content) > 29){
            return Utils.getFirstNWords(content, 30) + " ...";
        }else{
            return content;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
