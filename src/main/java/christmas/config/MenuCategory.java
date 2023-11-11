package christmas.config;

/**
 * 메뉴의 카테고리 정보를 담고 있는 Enum
 */
public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String category;

    MenuCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}