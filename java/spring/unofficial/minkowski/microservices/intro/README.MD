Docker commands

docker network create micro
docker network ls
docker run -d --name elasticsearch --net micro -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.6.2
docker run -d --name logstash --net micro -p 5000:5000 -v ~/logstash.conf:/usr/share/logstash/pipeline/logstash.conf docker.elastic.co/logstash/logstash:7.6.2
docker run -d --name kibana --net micro -p 5601:5601 -e "ELASTICSEARCH_URL=http://elasticsearch:9200" docker.elastic.co/kibana/kibana:7.6.2
