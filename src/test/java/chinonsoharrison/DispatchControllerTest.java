package chinonsoharrison;

import chinonsoharrison.controller.DispatchController;
import chinonsoharrison.enums.Model;
import chinonsoharrison.enums.State;
import chinonsoharrison.pojo.DronePojo;
import chinonsoharrison.pojo.Medication;
import chinonsoharrison.service.DispatchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DispatchControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void registerDrone() throws Exception {
        DronePojo pojo = DronePojo.builder()
        .serialNumber("8656DFBWHT435").model(Model.Cruiserweight)
        .state(State.IDLE).weightLimit(new Float(320))
        .batteryCapacity(99).build();

        JSONObject anObject = new JSONObject();
        BeanUtils.copyProperties(pojo, anObject);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);

        mvc.perform(post("/dispatch/drone/register").contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void loadMedicationItem() throws Exception {
        Medication pojo = Medication.builder()
        .code("688JJGH2982").name("Via")
        .weight(new Float(320)).build();

        JSONObject anObject = new JSONObject();
        BeanUtils.copyProperties(pojo, anObject);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);

        mvc.perform(post("/dispatch/loadMedicationItem/1").contentType(MediaType.APPLICATION_JSON).content(requestJson))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void loadMedicationItemFailed() throws Exception {
        Medication pojo = Medication.builder()
                .code("688JJGH2982").name("Via")
                .weight(new Float(320)).build();

        JSONObject anObject = new JSONObject();
        BeanUtils.copyProperties(pojo, anObject);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        mvc.perform(post("/dispatch/loadMedicationItem/0").contentType(MediaType.APPLICATION_JSON).content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkMedicationItem() throws Exception {
        mvc.perform(get("/dispatch/checkMedicationItem/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkMedicationItemFailed() throws Exception {
        mvc.perform(get("/dispatch/checkMedicationItem/0").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkBatteryLevel() throws Exception {
        mvc.perform(get("/dispatch/checkBatteryLevel/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkBatteryLevelFailed() throws Exception {
        mvc.perform(get("/dispatch/checkBatteryLevel/0").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkAvailableDrones() throws Exception {
        mvc.perform(get("/dispatch/checkAvailableDrones").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}
