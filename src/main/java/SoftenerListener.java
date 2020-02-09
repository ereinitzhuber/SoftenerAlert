import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@EnableAutoConfiguration
@RestController
public class SoftenerListener {
    @PostMapping("/status")
    public ResponseEntity controller(@RequestBody Data data) {
        if (data.status.contains("running")) {
            SendEmail.send();
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
