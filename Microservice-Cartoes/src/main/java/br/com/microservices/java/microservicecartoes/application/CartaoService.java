package br.com.microservices.java.microservicecartoes.application;

import br.com.microservices.java.microservicecartoes.domain.Cartao;
import br.com.microservices.java.microservicecartoes.infra.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;
    @Transactional
    public Cartao saveCartao(Cartao cartao){
        return repository.save(cartao);
    }

    public List<Cartao> listCartoesByRenda(Long renda){
        var rendaDecimal = BigDecimal.valueOf(renda);
        var cartoes =repository.findByRendaLessThanEqual(rendaDecimal);
        return cartoes.isEmpty() ? new ArrayList<>() : cartoes;
    }

}
