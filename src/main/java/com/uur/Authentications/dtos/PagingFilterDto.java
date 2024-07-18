package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingFilterDto <T>{
    private  int pageNumber;
    private  int size;
    private  T filterData;
}
