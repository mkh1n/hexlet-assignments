package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testShow() throws Exception {
        var task = createTask();
        var title = task.getTitle();

        var createResponse = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn();

        var createdTask = om.readValue(createResponse.getResponse().getContentAsString(), Task.class);

        var showResponse = mockMvc.perform(get("/tasks/" + createdTask.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var savedTask = om.readValue(showResponse.getResponse().getContentAsString(), Task.class);
        assertThat(savedTask.getTitle()).isEqualTo(title);
    }

    @Test
    public void testCreate() throws Exception {
        var task = createTask();
        var title = task.getTitle();

        var response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn();

        var createdTask = om.readValue(response.getResponse().getContentAsString(), Task.class);

        var savedTask = taskRepository.findById(createdTask.getId()).get();
        assertThat(savedTask.getTitle()).isEqualTo(title);
    }

    @Test
    public void testUpdate() throws Exception {
        var task = createTask();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "example");

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo("example");
    }

    @Test
    public void testDelete() throws Exception {
        var task = createTask();
        taskRepository.save(task);

        var request = delete("/tasks/" + task.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
        private Task createTask() {
            return Instancio.of(Task.class)
                    .ignore(Select.field(Task::getId))
                    .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                    .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(10))
                    .create();
        }
}