package Classes;

import java.io.Serializable;
import java.lang.reflect.Method;

public class List<X> implements Cloneable, Serializable {
    
    private No first;
    
    public List() {
        this.first = null;
    }

    private class No implements Serializable{

        private X info;
        private No next;

        public void setInfo(X i) {
            this.info = i;
        }

        public void setNext(No p) {
            this.next = p;
        }

        public X getInfo() {
            return this.info;
        }

        public No getNext() {
            return this.next;
        }

        public No(X i, No p) {
            this.setInfo(i);
            this.setNext(p);
        }

        public No(X i) {
            this(i, null);
        }
    }

    private X meuCloneDeX(X modelo) {
        //return (X)modelo.clone();
        X ret = null;
        try {
            Class<?> classe = modelo.getClass();
            Class<?>[] tipoDoParametroFormal = null; // null pq nao exist parametros
            Method metodo = classe.getMethod("clone", tipoDoParametroFormal);
            Object[] parametroReal = null; // null pq nao exist parametros
            ret = (X) metodo.invoke(modelo, parametroReal);
        } catch (Exception erro) {
        }
        return ret;
    }

    public void insertElementIndex(X element, int index) throws Exception {
        int size = this.getQtdElems();
        if (index < 0 || index > size + 1) {
            throw new Exception("Index nao valido!");
        }

        if (size == 0 || index == 1) {
            insertElementBeginning(element);
        } else if ((index == (size + 1)) || (index == 0)) {
            insertElementLast(element);
        }
    }

    private void insertElementBeginning(X i) throws Exception {
        if (i == null) {
            throw new Exception("Informacao ausente");
        }

        X info;
        if (i instanceof Cloneable) {
            info = meuCloneDeX(i);
        } else {
            info = i;
        }

        this.first = new No(info, this.first);
    }

    private void insertElementLast(X i) throws Exception {
        if (i == null) {
            throw new Exception("Informacao ausente");
        }

        X info;
        if (i instanceof Cloneable) {
            info = meuCloneDeX(i);
        } else {
            info = i;
        }

        if (this.first == null) {
            this.first = new No(info);
        } else {
            No current = this.first;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(new No(info));
        }
    }

    public int getQtdElems() {
        int cont = 0;

        No current = this.first;

        while (current != null) {
            cont++;
            current = current.getNext();
        }

        return cont;
    }

    public boolean exist(X i) {
        No current = this.first;

        while (current != null) {
            if (i.equals(current.getInfo())) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public void removerDoInicio() throws Exception {
        if (this.first == null) {
            throw new Exception("Lista vazia");
        }

        this.first = this.first.getNext();
    }

    public void removeInfo(X value) throws Exception {
        if (!exist(value)) {
            throw new Exception("Invalid info!");
        }
        if (this.first == null) {
            throw new Exception("Empty list!");
        }

        No current = this.first, aux = null;
        int countIndex = getElementIndex(value);
        if (countIndex == 1) {
            removerDoInicio();
            return;
        } else if (countIndex == this.getQtdElems()) {
            removerDoFim();
            return;
        }
        countIndex = 1;
        for (int i = 1; i < (this.getQtdElems()) - 1; i++) {
            if (current.getNext().getInfo() == value) {
                break;
            }

            current = current.getNext();
        }
        aux = current.getNext().getNext();
        current.setNext(null);
        current.setNext(aux);
    }

    public void removerDoFim() throws Exception {
        if (this.first == null) {
            throw new Exception("Lista vazia");
        }

        if (this.first.getNext() == null) {
            this.first = null;
        } else {
            No current = this.first;

            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }

            current.setNext(null);
        }
    }

    public int getElementIndex(X element) throws Exception {
        if (element == null) {
            throw new Exception("The element is emtpy");
        }
        int countIndex = 1;
        No current = this.first;
        while (current.getInfo() != element) {
            countIndex++;
            current = current.getNext();
        }
        return countIndex;
    }

    public X getElementList(int index) throws Exception {
        if (index < 0 || index > this.getQtdElems()) {
            throw new Exception("Invalid index");
        }
        
        if (index == 1) {
            return this.first.getInfo();
        }else {
            No aReturn = this.first;
            for (int i = 1; i < index; i++) {
                aReturn = aReturn.getNext();
            }

            return aReturn.getInfo();
        }
    }
    
    public boolean isEmpty(){
        return this.first == null;
    }

    // daqui para baixo sao os metodos obrigatorios; so faltou o compareTo, porque aqui, ele nao seria obrigatorio
    public String toString() {
        String ret = "{";

        No current = this.first;

        while (current != null) {
            ret = ret + current.getInfo();

            if (current.getNext() != null) {
                ret = ret + ",";
            }

            current = current.getNext();
        }

        return ret + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        List<X> lista = (List<X>) obj;

        No pThis = this.first;
        No pLista = lista.first;

        while (pThis != null && pLista != null) {
            if (!pThis.getInfo().equals(pLista.getInfo())) {
                return false;
            }

            pThis = pThis.getNext();
            pLista = pLista.getNext();
        }

        if (pThis != null || pLista != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int ret = 666;

        No current = this.first;

        while (current != null) {
            ret = 7 * ret + current.getInfo().hashCode();
            current = current.getNext();
        }

        return ret;
    }

    public List(List<X> modelo) throws Exception {
        if (modelo == null) {
            throw new Exception("Modelo ausente");
        }

        if (modelo.first == null) {
            return;
        }

        this.first = new No(modelo.first.getInfo());
        

        No atualThis = this.first, atualModelo = modelo.first;

        while (atualModelo.getNext() != null) {
            atualThis.setNext(new No(atualModelo.getNext().getInfo()));
            atualThis = atualThis.getNext();
            atualModelo = atualModelo.getNext();
        }
    }

    public Object clone() {

        List<X> ret = null;

        try {
            ret = new List<X>(this);
        } catch (Exception erro) {
        }

        return ret;
    }
}
