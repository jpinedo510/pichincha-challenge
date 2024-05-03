package pe.msauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateUserRequest implements Serializable {

    private static final long serialVersionUID = -5112120943627748815L;

    private String email;
}
