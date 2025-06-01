package org.example.hibernateserver.dto.common;

import java.util.List;

public record PaginatedData<T>(List<T> items, int page, int pageSize, long totalRecords, boolean hasMore) {
}
