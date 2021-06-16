package com.platformtest.dto.client;


import com.platformtest.model.Client;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto implements Common{

  @NotBlank(message = "Mandatory field")
  private String name;
  @NotNull(message = "Mandatory field")
  private Integer age;

  public static Client toEntity(ClientDto dto){
    return Client.builder()
        .age(dto.getAge())
        .name(dto.getName())
        .build();
  }
}
