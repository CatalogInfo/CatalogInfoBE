package com.example.CatalogInfoBE.models.table_entities;

import com.example.CatalogInfoBE.models.interfaces.Model;
import com.example.CatalogInfoBE.models.interfaces.ModelString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video implements ModelString {

    @Id
    private String id;
    private String link;
    private String channelTitle;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public String getId() {
        return id;
    }

}
