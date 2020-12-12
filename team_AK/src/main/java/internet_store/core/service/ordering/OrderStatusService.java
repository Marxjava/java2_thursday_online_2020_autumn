package internet_store.core.service.ordering;

import internet_store.core.domain.Order;
import internet_store.core.domain.TelegramChatId;
import internet_store.core.request.ordering.OrderStatusRequest;
import internet_store.core.request.telegram.FindTelegramChatIdRequest;
import internet_store.core.service.telegram.FindTelegramChatIdService;
import internet_store.database.order_database.InnerOrderDatabase;
import internet_store.integration.telegram.ChatBot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class OrderStatusService {
    @Autowired
    InnerOrderDatabase orderDatabase;
    @Autowired
    FindTelegramChatIdService telegramChatIdService;
    @Autowired
    ChatBot chatBot;
    private Order order;

    public void execute(OrderStatusRequest orderStatusRequest) {
        long orderId = orderStatusRequest.getOrderId();

        order = orderDatabase.findById(orderId);

        order.setOrderStatus(orderStatusRequest.getOrderStatus());

        List<TelegramChatId> clientChatId = tryFindClientChatId();
        clientChatId.forEach(this::printNewInformation);
    }

    private List<TelegramChatId> tryFindClientChatId() {
        FindTelegramChatIdRequest request = new FindTelegramChatIdRequest(order.getOrderNumber());
        return telegramChatIdService.execute(request);
    }

    @SneakyThrows(TelegramApiException.class)
    private void printNewInformation(TelegramChatId clientChatId) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(String.valueOf(clientChatId.getChatId()))
                .text("New information about order number: " + order.getOrderNumber()
                        + "\n" + "Order date: " + order.getOrderDate() + "\n"
                        + "Total sum: " + order.getTotalSum() + "\n"
                        + "Order status: " + order.getOrderStatus().toString())
                .build();
        chatBot.execute(sendMessage);
    }
}