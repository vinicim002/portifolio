package com.vinicius.backend.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Value("${resend.api.key}")
    private String apiKey;

    @Value("${resend.from}")
    private String emailFrom;

    @Value("${resend.to}")
    private String emailTo;

    /**
     * Envia email de contato via Resend
     */
    public void sendContactEmail(String name, String email, String subject, String body) {
        try {
            Resend resend = new Resend(apiKey);

            // HTML escapado para evitar XSS
            String htmlBody = buildEmailHtml(name, email, subject, body);

            CreateEmailOptions request = CreateEmailOptions.builder()
                    .from(emailFrom)
                    .to(emailTo)
                    .replyTo(email) // Responder direto pro visitante
                    .subject("📬 Nova mensagem: " + subject)
                    .html(htmlBody)
                    .build();

            resend.emails().send(request);
            log.info("✅ Email enviado com sucesso via Resend para: {}", emailTo);

        } catch (ResendException e) {
            log.error("❌ Erro ao enviar email via Resend: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
        }
    }

    /**
     * Constrói HTML do email com escaping de caracteres especiais
     * NÃO usa String.format para evitar problemas com emojis e caracteres especiais
     */
    private String buildEmailHtml(String name, String email, String subject, String body) {
        String escapedName = escapeHtml(name);
        String escapedEmail = escapeHtml(email);
        String escapedSubject = escapeHtml(subject);
        String escapedBody = escapeHtml(body).replace("\n", "<br>");

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <style>\n" +
                "    body { font-family: Arial, sans-serif; color: #333; line-height: 1.6; }\n" +
                "    .container { max-width: 600px; margin: 0 auto; padding: 20px; }\n" +
                "    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 20px; border-radius: 8px; margin-bottom: 20px; }\n" +
                "    .content { background: #f9f9f9; padding: 20px; border-left: 4px solid #667eea; margin: 20px 0; }\n" +
                "    .field { margin: 15px 0; }\n" +
                "    .label { font-weight: bold; color: #667eea; }\n" +
                "    .message { background: white; padding: 15px; border-radius: 5px; margin-top: 10px; }\n" +
                "    .footer { font-size: 12px; color: #999; margin-top: 30px; border-top: 1px solid #ddd; padding-top: 15px; }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <div class=\"header\">\n" +
                "      <h2>📨 Nova Mensagem de Contato</h2>\n" +
                "    </div>\n" +
                "    <div class=\"content\">\n" +
                "      <div class=\"field\">\n" +
                "        <span class=\"label\">👤 Nome:</span> " + escapedName + "\n" +
                "      </div>\n" +
                "      <div class=\"field\">\n" +
                "        <span class=\"label\">📧 Email:</span> " + escapedEmail + "\n" +
                "      </div>\n" +
                "      <div class=\"field\">\n" +
                "        <span class=\"label\">📌 Assunto:</span> " + escapedSubject + "\n" +
                "      </div>\n" +
                "      <div class=\"field\">\n" +
                "        <span class=\"label\">💬 Mensagem:</span>\n" +
                "        <div class=\"message\">\n" +
                "          " + escapedBody + "\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      <p>✉️ Para responder, clique em \"Responder\" ou envie um email para: <strong>" + escapedEmail + "</strong></p>\n" +
                "      <p>Esta é uma mensagem automática do seu portfólio.</p>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }

    /**
     * Escapa caracteres especiais HTML para evitar XSS
     */
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}