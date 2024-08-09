## Create a bucket

```sh
aws s3 mb s3://encryption-fun-om-134
```

## Create a file and Put Object with encryption SSE-S3

```sh
echo "Hello World" > hello.txt
aws s3 cp hello.txt s3://encryption-fun-om-134
```

## Put Object with encryption of SSE-KMS

```sh
aws s3api put-object \
--bucket encryption-fun-om-134 \
--key hello.txt \
--body hello.txt \
--server-side-encryption "aws:kms" \
--ssekms-key-id "254eb426-fedc-4a00-acef-14d26487a7ac" \
```

## Output

```sh
{
    "ETag": "\"e677781a8cbe6b507c2aacb636aa6472\"",
    "ServerSideEncryption": "aws:kms",
    "SSEKMSKeyId": "arn:aws:kms:us-east-1:484494363864:key/254eb426-fedc-4a00-acef-14d26487a7ac"
}
```

## Download file

```sh
aws s3 cp s3://encryption-fun-om-134/hello.txt hello.txt
```

## Put Object with SSE-C via aws s3

https://catalog.us-east-1.prod.workshops.aws/workshops/aad9ff1e-b607-45bc-893f-121ea5224f24/en-US/s3/serverside/ssec

```sh
openssl rand -out ssec.key 32

aws s3 cp hello.txt s3://encryption-fun-om-134/hello.txt \
--sse-c AES256 \
--sse-c-key fileb://ssec.key

aws s3 cp s3://encryption-fun-om-134/hello.txt hello.txt --sse-c AES256 --sse-c-key fileb://ssec.key
```

## Empty bucket

```sh
aws s3 rm s3://encryption-fun-om-134 --recursive
```

## Remove bucket
```sh
aws s3 rb s3://encryption-fun-om-134
```