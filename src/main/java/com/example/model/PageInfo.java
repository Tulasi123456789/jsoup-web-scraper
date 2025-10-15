package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    private String pageUrl;
    private String htmlContent;
    private List<LinkInfo> links;

}
