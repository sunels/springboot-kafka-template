package com.example.dubara;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage  implements Serializable {
    private Integer id;
    private String bookName;
}
