/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.social.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Arthur
 */
@Controller
public class TelaUploadController {

    @RequestMapping(value = "/uploadImagem", method = RequestMethod.GET)
    public String uploadImagem() {
        return "uploadImagem";
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

        try {
            // Get the filename and build the local file path (be sure that the 
            // application have write permissions on such directory)
            String filename = uploadfile.getOriginalFilename();
            String directory = "C:\\Users\\Arthur\\Documents\\GitHub\\FaceFut\\FaceFut\\social\\social-web\\src\\main\\resources\\static\\imgs";
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream
                    = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadfile.getBytes());
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    } // method uploadFile
}
