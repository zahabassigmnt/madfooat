package com.madfooat.files.output;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.madfooat.files.input.InRecordDTO;

@Component
public class OutFileProcessor {

	BigDecimal totalTrx;

	public File processOutFile(List<InRecordDTO> inputList,String fileName) throws IOException {

		totalTrx = new BigDecimal(0.00);

		StringBuilder records = new StringBuilder();
		// Success Trx processing
		records.append("S");
		records.append(",");
		records.append(inputList.stream().filter(x -> x.isValidationStatus()).map(mapToItem)
				.collect(Collectors.toList()).size());
		records.append(",");
		records.append(totalTrx);
		records.append("\r\n");

		totalTrx = new BigDecimal(0.00);
		
		// Fail Trx processing
		records.append("F");
		records.append(",");
		records.append(inputList.stream().filter(x -> !x.isValidationStatus()).map(mapToItem)
				.collect(Collectors.toList()).size());
		records.append(",");
		records.append(totalTrx);
		
		Path path = Files.createTempFile(fileName, ".csv");
        Files.write(path, records.toString().getBytes(StandardCharsets.UTF_8));
        
        System.out.println("Generating temp file >> " + path.toFile().getAbsolutePath());

		return path.toFile();
	}

	private Function<InRecordDTO, String> mapToItem = (record) -> {
		totalTrx = totalTrx.add(record.getAmount());
		return "";
	};
}
