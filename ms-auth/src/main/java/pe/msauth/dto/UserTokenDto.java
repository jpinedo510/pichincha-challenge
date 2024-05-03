package pe.msauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenDto implements Serializable {

    private static final long serialVersionUID = 6980613972871536419L;

    private String token;
    private Integer userId;
}
