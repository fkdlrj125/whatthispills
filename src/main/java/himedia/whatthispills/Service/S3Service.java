package himedia.whatthispills.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {
	private	final AmazonS3 amazonS3;
	
	public S3Service(AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
	}
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public String saveFile(MultipartFile multipartFile, Long idx, String category) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(multipartFile.getSize());
		metadata.setContentType(multipartFile.getContentType());
		
		amazonS3.putObject(bucket, idx.toString(), multipartFile.getInputStream(), metadata);
		return amazonS3.getUrl(bucket, idx.toString()).toString();
	}
	
	public String getImageUrl(Long idx) {
		return amazonS3.getUrl(bucket, idx.toString()).toString();
	}
	
	public void deleteFile(Long idx) {
		amazonS3.deleteObject(bucket, idx.toString());
	}
}
