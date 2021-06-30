package com.github.noodzhan.main.structure.tree;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/6/27 11:10 上午
 */
public class DaoEntity {
    private Long id;
    private String label;
    private Long pid;

    public DaoEntity(Long id, String label, Long pid) {
        this.id = id;
        this.label = label;
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DaoEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", pid=" + pid +
                '}';
    }
}
