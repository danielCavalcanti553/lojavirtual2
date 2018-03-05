package com.tcc.lojavirtual.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.lojavirtual.service.exception.FileException;

@Service
public class ImageService {

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		// pegando a extensão do arquivo
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		
		// Verificar se o arquivo é jpg ou png
		if(!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens png ou jpg são permitidas");
		}
		
		// obter buffer a partir do multipartFile
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			// Verificar se o arquivo for png, converte para jpg
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
			
		} catch (IOException e) {
			throw new FileException("Erro ao ler aquivo");
		}
		
	}

	// Função para converter JPG em PNG - Código padrão
	public BufferedImage pngToJpg(BufferedImage img) {
		// Convertendo
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		// Preencher para fundo branco o PNG (transparente)
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	// Retornar um InputStrem a partir de um buffer de image -> Código padrão
	// Necessário para o bucker S3, que recebe como InputStream
	public InputStream getInputStrem(BufferedImage img,String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		}catch(IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}
	
	// Cropar Imagem para ficar quadrada
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		// descobre o menor (heightxwidth)
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		// recortar a image
		return Scalr.crop(sourceImg, (sourceImg.getWidth()/2) - (min/2), (sourceImg.getHeight()/2) - (min/2), min,min);
	}
	
	// Redimensionar imagem conforme configuração
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size); // Ultra-> Perda menor de qualidade
	}
	
}
