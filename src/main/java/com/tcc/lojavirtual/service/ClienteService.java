package com.tcc.lojavirtual.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.lojavirtual.security.UserSecurity;
import com.tcc.lojavirtual.service.UserService;
import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.domain.enums.Perfil;
import com.tcc.lojavirtual.dto.ClienteDTO;
import com.tcc.lojavirtual.dto.ClienteNewDTO;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.service.exception.AuthorizationException;
import com.tcc.lojavirtual.service.exception.DataIntegrityException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder passCrypt;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	

	@Value("${img.profile.size}")
	private int size;
	
	public Cliente find(Integer id) {
		// pegar o usuário logado
		UserSecurity user = UserService.authenticated();
		
		// Verificar se o usuário logado tem autorização para visualizar o usuário solicitado 
		//e não for administrador lança um AuthorizationException
		if(user==null ||  !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) 
			throw new AuthorizationException("Acesso Negado!");
		
		
		Cliente obj = clienteRepository.findOne(id);
		if (obj == null)
			throw new NotFoundObjectException("Cliente Não encontrado! " + id + ", Tipo " + Cliente.class.getName());
		return obj;
	}
	
	public Cliente findByEmail(String email) {
		// Mesma implementação do find mas utilizando email
		UserSecurity user = UserService.authenticated();
		if(user==null ||  !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) 
			throw new AuthorizationException("Acesso Negado!");
		
		Cliente obj = clienteRepository.findOne(user.getId());
		if (obj == null)
			throw new NotFoundObjectException("Cliente Não encontrado! Id: " + user.getId() + ", Tipo " + Cliente.class.getName());
		return obj;
	}

	public Cliente insert(Cliente obj) {
		obj.setCodigoCliente(null);
		return clienteRepository.save(obj);
	}

	public Cliente fromNewDTO(ClienteNewDTO obj) {
		Cliente c = new Cliente(null, obj.getCpf(), obj.getNome(), obj.getEndereco(), obj.getMunicipio(),
				obj.getEstado(), obj.getTelefone(), obj.getEmail(), passCrypt.encode(obj.getSenha()));
		return c;
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPage, String order, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction), order);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getCodigoCliente());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	

	public Cliente fromDTO(ClienteDTO objDto) {

		Cliente c = new Cliente(null, objDto.getNome(), objDto.getNome(), objDto.getEndereco(), objDto.getMunicipio(),
				objDto.getEstado(), objDto.getTelefone(), objDto.getEmail(), objDto.getSenha());

		return c;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}
	
	
	// Serviço de Upload de imagem (S3Service)
	public URI uploadProfilePicture(MultipartFile multiPart) {
		UserSecurity user = UserService.authenticated();

	if (user == null) {
		throw new AuthorizationException("Acesso negado");
	}

	// Extrair arquivo JPG da requisição
	BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPart);
	jpgImage = imageService.cropSquare(jpgImage);
	jpgImage = imageService.resize(jpgImage, size);

	// definindo nome do arquivo
	String fileName = prefix + user.getId() + ".jpg";

	return s3Service.uploadFile(imageService.getInputStrem(jpgImage, "jpg"), fileName, "image");
	}
	
	/*
			UserSS user = UserService.authenticated();

		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		// Extrair arquivo JPG da requisição
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multiPart);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		// definindo nome do arquivo
		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStrem(jpgImage, "jpg"), fileName, "image");
	 * 
	 * */
	


}
