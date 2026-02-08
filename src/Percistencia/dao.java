ackage Persistencia;

import java.util.List;

import javax.persistence.*;

import java.util.Date;

import java.util.Map;

public class dao {

    // Este método agora é o único que você precisa para salvar e atualizar.
    // O ".merge()" é inteligente: se o objeto não tem ID, ele cria (INSERT).
    // Se o objeto já tem um ID, ele atualiza (UPDATE).
    // MÉTODO 'salvar' ATUALIZADO PARA RETORNAR O OBJETO
    public static Object salvar(Object objeto) {

        EntityManager ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        ent.getTransaction().begin();

        Object objetoGerenciado = ent.merge(objeto); // O merge retorna a instância gerenciada

        ent.getTransaction().commit();

        ent.close();

        return objetoGerenciado; // Retorna o objeto com o ID preenchido

    }

    // Este método é usado para buscar um objeto pela sua chave primária (ID/código).
    // É o método mais seguro para carregar um objeto para edição ou exclusão.
    public static Object consultar(Class<?> clazz, Object pk) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Object o = ent.find(clazz, pk);

        ent.close();

        return o;

    }

    // Usado para excluir um objeto que já foi carregado do banco.
    public static void excluir(Object objeto) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        ent.getTransaction().begin();

        // O merge é importante para garantir que o objeto está no contexto de persistência antes de remover
        ent.remove(ent.merge(objeto));

        ent.getTransaction().commit();

        ent.close();

    }

    // Seus métodos de listagem continuam perfeitos para buscar com base em NamedQueries.
    public static List listar(String nomePesquisa) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createNamedQuery(nomePesquisa);

        List lista = q.getResultList();

        ent.close();

        return lista;

    }

    public static List listar(String nomePesquisa, String parametro, String chave) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createNamedQuery(nomePesquisa);

        q.setParameter(parametro, chave);

        List lista = q.getResultList();

        ent.close();

        return lista;

    }

    public static List listar(String nomePesquisa, String parametro, Object chave) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createNamedQuery(nomePesquisa);

        q.setParameter(parametro, chave);

        List lista = q.getResultList();

        ent.close();

        return lista;

    }

    public static List executarConsulta(String consulta) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createQuery(consulta);

        List lista = q.getResultList();

        ent.close();

        return lista;

    }

    public static Object consultarAgregado(String nomePesquisa, String param1, Date valor1, String param2, Date valor2) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createNamedQuery(nomePesquisa);

        q.setParameter(param1, valor1, TemporalType.DATE);

        q.setParameter(param2, valor2, TemporalType.DATE);

        Object resultado = q.getSingleResult();

        ent.close();

        return resultado;

    }

    public static List listar(String nomePesquisa, String param1, Date valor1, String param2, Date valor2) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createNamedQuery(nomePesquisa);

        q.setParameter(param1, valor1, TemporalType.DATE);

        q.setParameter(param2, valor2, TemporalType.DATE);

        List lista = q.getResultList();

        ent.close();

        return lista;

    }

    public static Object consultarAgregadoSimples(String nomePesquisa) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        Query q = ent.createNamedQuery(nomePesquisa);

        Object resultado = q.getSingleResult();

        ent.close();

        return resultado;

    }

    /**
     *
     * Lista os resultados de forma paginada. Ótimo para tabelas que mostram "10
     *
     * itens por página".
     *
     */
    public <T> List<T> listarPaginado(String namedQuery, Map<String, Object> params, Class<T> resultClass, int firstResult, int maxResults) {

        EntityManager ent;

        ent = Persistence.createEntityManagerFactory("UP").createEntityManager();

        TypedQuery<T> query = ent.createNamedQuery(namedQuery, resultClass);

        if (params != null) {

            for (Map.Entry<String, Object> entry : params.entrySet()) {

                query.setParameter(entry.getKey(), entry.getValue());

            }

        }

        // Define qual o primeiro registro (ex: página 2 começa no item 10)
        query.setFirstResult(firstResult);

        // Define quantos registros trazer (ex: 10 por página)
        query.setMaxResults(maxResults);

        return query.getResultList();

    }

}
