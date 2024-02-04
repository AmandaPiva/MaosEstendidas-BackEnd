package com.tcc.maosestendidas.JWT.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//DTO QUE VAI RETORNAR ESSAS INFORMAÇÕES DO USUÁRIO
public class JwtAuthResponseDTO {
    private String token;
    private String email;
    private String role;
}
