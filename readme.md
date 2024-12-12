## Microservices simples utilizando RabbitMQ rodando no minikube


## Instalação do Helm
```
https://helm.sh/docs/intro/install/

```

## Instalação do minikube
https://kubernetes.io/docs/tasks/tools/install-minikube/
 

## Subir minikube
```

// iniciar padrão
minikube start

// iniciar alocando memoria e processador
minikube start --cpus 4 --memory 8192


```

## Instalar o kubectl
```
sudo snap install kubectl --classic  
```

## Comandos do kubernetes
```
kubectl get pods
kubectl get pods --all-namespaces
```

## Configurando o Docker Registry
```
minikube addons list
minikube addons enable registry  
kubectl get pods -n kube-system
kubectl port-forward --namespace kube-system registry-wndk5 5000:5000
```

## Criando diretorio no minikube e subindo a imagem para docker
```
minikube mount c:\Ambiente\Projetos\rabbitmq:/projetos
minikube ssh
docker build -t localhost:5000/rabbitmq-springboot:latest .
docker push localhost:5000/rabbitmq-springboot


```
## Aplicando o manifesto

```
Certifique-se de que os manifestos Kubernetes (por exemplo, deployment.yaml) estão configurados corretamente. Em seguida, aplique-os:
kubectl apply -f deployment.yaml
```

