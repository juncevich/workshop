kubectl create secret generic jwt-secret --from-literal=jwt=asdf

kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission
