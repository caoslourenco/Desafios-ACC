package acc.br.student_registration.service;

import org.springframework.stereotype.Service;

import acc.br.student_registration.client.ViaCepClient;
import acc.br.student_registration.dto.AddressDTO;
import acc.br.student_registration.exception.AddressNotFoundException;

@Service
public class AddressService {

    private final ViaCepClient viaCepClient;

    public AddressService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    /**
     * Obtém o endereço a partir do CEP fornecido, utilizando o cliente ViaCep.
     *
     * @param cep o CEP para buscar o endereço
     * @return o DTO contendo os dados do endereço
     * @throws AddressNotFoundException se o endereço não for encontrado ou for inválido
     */
    public AddressDTO getAddress(String cep) {
        // Chama o cliente ViaCep para buscar o endereço
        AddressDTO addressDTO = viaCepClient.getAddressByCep(cep);

        // Verifica se o endereço foi encontrado
        if (addressDTO == null || addressDTO.getCep() == null) {
            throw new AddressNotFoundException("Address not found for CEP: " + cep);
        }

        return addressDTO;
    }
}
