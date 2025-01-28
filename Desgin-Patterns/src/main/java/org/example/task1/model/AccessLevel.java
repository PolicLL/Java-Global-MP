package org.example.task1.model;

public enum AccessLevel {
  GUEST(0),
  USER(1),
  MANAGER(2),
  ADMIN(3);

  private final int level;

  AccessLevel(int level) {
    this.level = level;
  }

  public int comparePriority(AccessLevel other) {
    return Integer.compare(this.level, other.level);
  }

  public boolean canAccess(AccessLevel requiredLevel) {
    return this.comparePriority(requiredLevel) >= 0;
  }

  public static AccessLevel fromString(String accessLevel) {
    return switch (accessLevel.toLowerCase()) {
      case "guest" -> GUEST;
      case "user" -> USER;
      case "manager" -> MANAGER;
      case "admin" -> ADMIN;
      default -> throw new IllegalArgumentException("Unknown access level: " + accessLevel);
    };
  }
}
