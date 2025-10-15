package sgu.sa.application.usecase.query.getuserorders;

import sgu.sa.application.usecase.common.Request;

import java.util.UUID;

public record GetUserOrdersQuery(
    UUID userId
) implements Request<GetUserOrdersResult> {
}
