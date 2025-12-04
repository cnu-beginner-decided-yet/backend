package hello.blog_clonecoding.comment.controller;

import hello.blog_clonecoding.comment.dto.CommentRequestDto;
import hello.blog_clonecoding.comment.dto.CommentResponseDto;
import hello.blog_clonecoding.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentService commentService;

    @Test
    void findAllComment() throws Exception {
        // given
        CommentResponseDto dto = CommentResponseDto.builder()
                .id(1L)
                .content("내용")
                .postId(1L)
                .userId(1L)
                .build();
        Mockito.when(commentService.findAllComment())
                .thenReturn(List.of(dto));

        // when & then
        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].content").value("내용"));
    }

    @Test
    void createComment() throws Exception {
        CommentResponseDto dto = CommentResponseDto.builder()
                .id(1L)
                .content("내용")
                .postId(1L)
                .userId(1L)
                .build();

        Mockito.when(commentService.createComment(any(CommentRequestDto.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"내용\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.content").value("내용"));
    }

    @Test
    void updateComment() throws Exception {
        CommentResponseDto dto = CommentResponseDto.builder()
                .id(1L)
                .content("수정된 내용")
                .postId(1L)
                .userId(1L)
                .build();

        Mockito.when(commentService.updateComment(eq(1L), any(CommentRequestDto.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"수정된 내용\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("수정된 내용"));
    }

    @Test
    void deleteComment() throws Exception {
        Mockito.doNothing().when(commentService).deleteComment(1L);

        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isNoContent());
    }

}
