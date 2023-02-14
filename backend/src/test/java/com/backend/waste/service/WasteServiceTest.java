package com.backend.waste.service;

import com.backend.util.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class WasteServiceTest extends ServiceTest {

    @Autowired
    private WasteService wasteService;

//    @Test
//    @DisplayName("업로드된 이미지로 폐기물이 등록됩니다")
//    void upload() throws IOException {
//        //given
//        MultipartFile imageFiles = new MockMultipartFile("test1","test1.PNG", MediaType.IMAGE_PNG_VALUE,"test1".getBytes());
//
//
//        //when
//        wasteService.postWasteImage(memberIdx,imageFiles);
//
//        //then
//        assertThat(wasteRepository.count()).isEqualTo(1);
//    }

}
