package com.cengzayhn.mini_banking_api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class FilterDTO {
    private String searchText = "";
    private int currentPage;
    private int pageSize;
    private String sortingColumn;
    private boolean asc;
}