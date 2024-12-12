package acc.br.student_registration.service;

import org.springframework.stereotype.Service;

import acc.br.student_registration.dto.AddressDTO;

@Service
public class AddressService {

    public AddressDTO getAddress(String cep) {
        // Aqui você deve implementar a lógica para buscar o endereço pelo CEP.
        // Pode ser uma chamada de API ou uma consulta ao banco de dados.
        
        AddressDTO address = new AddressDTO();
        address.setCep(cep);
        address.setStreet("Rua Exemplo");
        address.setNeighborhood("Bairro Exemplo");
        address.setCity("Cidade Exemplo");
        address.setState("Estado Exemplo");
        return address;
    }
}
