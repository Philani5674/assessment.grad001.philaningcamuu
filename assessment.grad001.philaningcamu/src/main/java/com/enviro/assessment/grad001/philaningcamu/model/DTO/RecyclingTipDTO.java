package com.enviro.assessment.grad001.philaningcamu.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecyclingTipDTO {
    private Long id;
    private String title;
    private String content;
    private Long wasteItemId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWasteItemId() {
        return wasteItemId;
    }

    public void setWasteItemId(Long wasteItemId) {
        this.wasteItemId = wasteItemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}