package com.uur.Authentications.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PagingFilterDto <T>{
    private  int pageNumber;
    private  int size;
    private  T filterData;
}
