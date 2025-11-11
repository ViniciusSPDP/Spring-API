package com.fatec.comercio.config;

import com.fatec.comercio.models.*;
import com.fatec.comercio.service.*;
// --- NOVAS IMPORTAÇÕES NECESSÁRIAS ---
import com.fatec.comercio.dto.VendaForm;
import com.fatec.comercio.dto.VendaProdutoForm;
import java.util.ArrayList;
import java.util.List;
// --- FIM DAS NOVAS IMPORTAÇÕES ---

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;
// Set e HashSet não são mais necessários para a Venda
// import java.util.HashSet;
// import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    // Injeção de todos os serviços necessários
    private final BairroService bairroService;
    private final CepService cepService;
    private final CidadeService cidadeService;
    private final ClienteService clienteService;
    private final MarcaService marcaService;
    private final ProdutoService produtoService;
    private final RuaService ruaService;
    private final SexoService sexoService;
    private final TipoService tipoService;
    private final UfService ufService;
    private final VendaService vendaService;

    public DataInitializer(BairroService bairroService, CepService cepService, CidadeService cidadeService, ClienteService clienteService, MarcaService marcaService, ProdutoService produtoService, RuaService ruaService, SexoService sexoService, TipoService tipoService, UfService ufService, VendaService vendaService) {
        this.bairroService = bairroService;
        this.cepService = cepService;
        this.cidadeService = cidadeService;
        this.clienteService = clienteService;
        this.marcaService = marcaService;
        this.produtoService = produtoService;
        this.ruaService = ruaService;
        this.sexoService = sexoService;
        this.tipoService = tipoService;
        this.ufService = ufService;
        this.vendaService = vendaService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando a inserção de dados iniciais...");

        // --- Nível 1: Entidades sem dependências ---
        
        Uf ufSP = new Uf();
        ufSP.setNomeuf("São Paulo");
        ufSP.setSiglauf("SP");
        Uf savedUf = ufService.salvarUf(ufSP);

        Sexo sexoMasc = new Sexo();
        sexoMasc.setNomesexo("Masculino");
        Sexo savedSexo = sexoService.salvarSexo(sexoMasc);

        Marca marcaEx = new Marca();
        marcaEx.setNomemarca("Marca Exemplo");
        Marca savedMarca = marcaService.salvarMarca(marcaEx);

        Tipo tipoEx = new Tipo();
        tipoEx.setNometipo("Eletrônicos");
        Tipo savedTipo = tipoService.salvarTipo(tipoEx);

        Bairro bairroCentro = new Bairro();
        bairroCentro.setNomebairro("Centro");
        Bairro savedBairro = bairroService.salvarBairro(bairroCentro);

        Cep cepEx = new Cep();
        cepEx.setNumerocep("15775-000");
        Cep savedCep = cepService.salvarCep(cepEx);

        Rua ruaEx = new Rua();
        ruaEx.setNomerua("Avenida Principal");
        Rua savedRua = ruaService.salvarRua(ruaEx);

        // --- Nível 2: Entidades que dependem do Nível 1 ---

        Cidade cidadeEx = new Cidade();
        cidadeEx.setNomecidade("Santa Fé do Sul");
        cidadeEx.setUf(savedUf);
        Cidade savedCidade = cidadeService.save(cidadeEx);

        Produto produtoEx = new Produto();
        produtoEx.setNomeproduto("Smartphone Exemplo");
        produtoEx.setQuantidade(50); // <-- Inicia com 50 no estoque
        produtoEx.setValor(1250.99);
        produtoEx.setMarca(savedMarca);
        produtoEx.setTipo(savedTipo);
        Produto savedProduto = produtoService.save(produtoEx);

        // --- Nível 3: Entidades que dependem do Nível 2 ---

        Cliente clienteEx = new Cliente();
        clienteEx.setNomecliente("José da Silva");
        clienteEx.setCpf("12345678901");
        clienteEx.setDatanasc(new Date()); // Data atual como exemplo
        clienteEx.setNumerocasa("500");
        clienteEx.setSexo(savedSexo);
        clienteEx.setBairro(savedBairro);
        clienteEx.setCidade(savedCidade);
        clienteEx.setRua(savedRua);
        clienteEx.setCep(savedCep);
        Cliente savedCliente = clienteService.save(clienteEx);

        // --- NÍVEL 4: ATUALIZADO PARA USAR DTOS ---

        // 1. Criar o DTO do item da venda
        VendaProdutoForm itemForm = new VendaProdutoForm();
        itemForm.setProdutoId(savedProduto.getCodproduto()); // Passa o ID do produto salvo
        itemForm.setQuantidadev(1); // Vendendo 1 unidade
        itemForm.setValorv(savedProduto.getValor()); // Passa o valor do produto

        // 2. Criar a lista de DTOs de itens
        List<VendaProdutoForm> itensForm = new ArrayList<>();
        itensForm.add(itemForm);

        // 3. Criar o DTO principal da Venda
        VendaForm vendaForm = new VendaForm();
        vendaForm.setClienteId(savedCliente.getCodcliente()); // Passa o ID do cliente salvo
        vendaForm.setProdutos(itensForm);

        // 4. Chamar o VendaService com o DTO (Form)
        vendaService.save(vendaForm);
        
        System.out.println(">>> Dados iniciais inseridos com sucesso! <<<");
        System.out.println(">>> Estoque do 'Smartphone Exemplo' deve ter sido atualizado para 49. <<<");
    }
}