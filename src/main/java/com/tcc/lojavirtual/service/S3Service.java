package com.tcc.lojavirtual.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.tcc.lojavirtual.service.exception.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;
	
	
	// Upload de Imagem -> Recebendo dados da requisição do meu sistema
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
	}

	// Enviando os dados para o servidor da Amazon
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}
}

/*
 * TESTE ----vvvvv
 * 
 * package com.daniel.cursomc.services;
 * 
 * import java.io.File;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.stereotype.Service;
 * 
 * import com.amazonaws.AmazonClientException; import
 * com.amazonaws.AmazonServiceException; import
 * com.amazonaws.services.s3.AmazonS3; import
 * com.amazonaws.services.s3.model.PutObjectRequest;
 * 
 * @Service public class S3Service {
 * 
 * private Logger LOG = LoggerFactory.getLogger(S3Service.class);
 * 
 * @Autowired private AmazonS3 s3client;
 * 
 * @Value("${s3.bucket}") private String buckteName;
 * 
 * public void uploadFile(String localFilePath) { try {
 * LOG.info("Inicio UPLOAD"); File file = new File(localFilePath);
 * s3client.putObject(new PutObjectRequest(buckteName, "teste.png", file));
 * LOG.info("Fim UPLOAD"); }catch(AmazonServiceException e) {
 * LOG.info("AmazonException " + e.getErrorMessage()); LOG.info("Code " +
 * e.getErrorCode()); }catch(AmazonClientException e) {
 * LOG.info("AmazonException " + e.getMessage());
 * 
 * }
 * 
 * } }
 * 
 * 
 */
