# Create Website 1

## Create a bucket
```sh
aws s3 mbs 3://cors-fun-omw-5232
```
## Change block public access / Allow public access
```sh
aws s3api put-public-access-block \
    --bucket cors-fun-omw-5232 \
    --public-access-block-configuration "BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=false,RestrictPublicBuckets=false"
```

## Retrieve block public access configuration

```sh
    aws s3api get-public-access-block \
    --bucket cors-fun-omw-5232
```

## Create a bucket policy

```sh
  aws s3api put-bucket-policy --bucket cors-fun-omw-5232 --policy file://bucket-policy.json
```

## Turn on static website hosting

## Static website configuration
```sh
aws s3api put-bucket-website --bucket cors-fun-omw-5232 --website-configuration file://website.json
```
## Upload our index.html file and include a resource that would be cross-origin

aws s3 cp index.html s3://cors-fun-omw-5232 

## Visit the website at the endpoint

http://cors-fun-omw-5232.s3-website.us-east-1.amazonaws.com

# Create Website 2

## Create a bucket
```sh
aws s3 mb s3://cors-fun2-omw-5232
```
## Change block public access / Allow public access
```sh
aws s3api put-public-access-block \
    --bucket cors-fun2-omw-5232 \
    --public-access-block-configuration "BlockPublicAcls=true,IgnorePublicAcls=true,BlockPublicPolicy=false,RestrictPublicBuckets=false"
```

## Retrieve block public access configuration

```sh
    aws s3api get-public-access-block \
    --bucket cors-fun2-omw-5232
```

## Create a bucket policy

```sh
  aws s3api put-bucket-policy --bucket cors-fun2-omw-5232 --policy file://bucket-policy2.json
```

## Turn on static website hosting

## Static website configuration
```sh
aws s3api put-bucket-website --bucket cors-fun2-omw-5232 --website-configuration file://website.json
```
## Upload our index.html file and include a resource that would be cross-origin

aws s3 cp hello.js s3://cors-fun2-omw-5232 

## Visit the website at the endpoint

http://cors-fun2-omw-5232.s3-website.us-east-1.amazonaws.com


## Create API Gatewat with mock response and then test the endpoint

curl -X POST -H "Content-Type: application/json" https://u49hbbvb0i.execute-api.us-east-1.amazonaws.com/prod/hello

## Apply a CORS policy

```sh
aws s3api put-bucket-cors --bucket cors-fun-omw-5232 --cors-configuration file://cors.json
```