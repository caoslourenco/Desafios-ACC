package acc.br.bean.life.cycle.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Item implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, BeanFactoryAware {
    
    // Atributos
    private String name;
    private int health;
    private String beanName;
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    // Construtor
    public Item(String name) {
        this.name = name;
        System.out.println("1. Instantiation: A new item has been created.");
    }

    // Getter
    public String getName() {
        return name;
    }

    // Métodos de Aware (Ordem de execução: BeanNameAware -> BeanFactoryAware -> ApplicationContextAware)
    @Override
    public void setBeanName(String name) {
        System.out.println("2. BeanNameAware: Setting bean name: " + name);
        this.beanName = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("3. BeanFactoryAware: Setting bean factory for Item: " + getName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4. ApplicationContextAware: Setting application context for Item.");
        this.applicationContext = applicationContext;
    }

    // Métodos de Inicialização (Ordem: afterPropertiesSet -> @PostConstruct -> customInit)
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7. Initialization: Item " + getName() + " is undergoing intensive training.");
    }

    @PostConstruct
    public void init() {
        System.out.println("6. @PostConstruct: Item " + getName() + " is being initialized.");
    }

    public void customInit() {
        System.out.println("8. Custom Initialization: Executing custom init for : " + getName());
    }

    // Métodos de Destruição (Ordem: @PreDestroy -> destroy -> customDestroy -> restAndRecover)
    @PreDestroy
    public void preDestroy() {
        System.out.println("10. @PreDestroy: Item " + getName() + " is being prepared for destruction.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("11. DisposableBean: Item " + getName() + " is saying goodbye and resting.");
    }

    public void customDestroy() {
        System.out.println("13. Custom Destruction: Performing final actions for : " + getName() + " is bidding farewell and performing a final action.");
    }

    private void restAndRecover() {
        System.out.println("12. DisposableBean: " + getName() + " is resting and recovering energy.");
    }
}
