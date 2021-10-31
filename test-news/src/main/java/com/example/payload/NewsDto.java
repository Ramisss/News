package com.example.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    private UUID id;

    private String newsTitle;

    private String newsText;

    private boolean approvedByAdmin;
}
