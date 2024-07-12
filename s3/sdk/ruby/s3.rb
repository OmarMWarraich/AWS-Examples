require "aws-sdk-s3"        # AWS SDK for Ruby to interact with S3
require "securerandom"      # For generating random UUIDs

# Get the bucket name from environment variable
bucket_name = ENV["BUCKET_NAME"]

# Specify the region for the S3 bucket
region = "us-east-1"

# Create an S3 client
client = Aws::S3::Client.new

# Create a new S3 bucket
begin
    if region == 'us-east-1'
      client.create_bucket({
        bucket: bucket_name
      })
    else
      client.create_bucket({
        bucket: bucket_name, 
        create_bucket_configuration: {
          location_constraint: region, 
        }, 
      })
    end
    puts "Bucket '#{bucket_name}' created successfully."
  rescue Aws::S3::Errors::BucketAlreadyOwnedByYou
    puts "Bucket '#{bucket_name}' already exists and is owned by you."
  rescue Aws::S3::Errors::ServiceError => e
    puts "Error creating bucket: #{e.message}"
end

# Generate a random number of files between 1 and 6
num_files = 1 + rand(6)
puts "number of files: #{num_files}"

# Iterate over the range from 1 to num_files to create each file
(1..num_files).each do |i|
  puts "Creating file number: #{i}"
  
  # Generate a filename
  filename = "file_#{i}.txt"
  
  # Define the path to store the file temporarily
  output_path = "/tmp/#{filename}"

  # Write a random UUID to the file
  File.open(output_path, "w") do |f|
    f.write SecureRandom.uuid
  end

  # Read the file and upload it to S3
  File.open(output_path, 'rb') do |f|
    client.put_object(
      bucket: bucket_name,
      key: filename,
      body: f
    )
  end
end
