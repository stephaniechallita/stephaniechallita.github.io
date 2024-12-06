import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import accounts.Account;
import transactions.Transaction;

public class Import {

    public static Accounting parse(String pathFileName) throws Exception {
        final List<Transaction> transactions = new ArrayList<>();
        final Set<String> accountLabels = new HashSet<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFileName))) {
            bufferedReader.lines()
                .forEach(line -> {
                    final String[] fields = line.split(",");
                    transactions.add(
                        new Transaction(
                            fields[0].trim(),
                            fields[1].trim(),
                            fields[2].trim(),
                            fields[3].trim(),
                            fields[4].trim(),
                            Integer.parseInt(fields[5].trim())
                        )
                    );
                    accountLabels.add(fields[3]);
                    accountLabels.add(fields[4]);
                });
        }

        return new Accounting(
            accountLabels.stream().map(Account::new).toList(),
            transactions
        );
    }
}

