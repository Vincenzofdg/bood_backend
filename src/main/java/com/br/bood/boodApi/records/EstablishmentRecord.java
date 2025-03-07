package com.br.bood.boodApi.records;

public record EstablishmentRecord(String name, String email, String tags, String description, Integer phone, Integer document, Float rate, Boolean status, String preview_url) {
}
