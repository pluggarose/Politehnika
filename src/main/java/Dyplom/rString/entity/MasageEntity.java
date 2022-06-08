package Dyplom.rString.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "massages")
public class MasageEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "string_text")
    private String stext;
    @Column(name = "string_speed")
    private Long string_speed;
    @Column(name = "string_color_type")
    private Long string_color_type;
    @Column(name = "string_color")
    private Long string_color;
    @Column(name = "string_timing_type")
    private String string_timing_type;
    @Column(name = "string_timing")
    private String string_timing;
    @Column(name = "showed")
    private Long showed;



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "string_massage",
            joinColumns = { @JoinColumn(name = "massage_id") },
            inverseJoinColumns = { @JoinColumn(name = "string_id") })


    private Set<RstringsEntity> strings = new HashSet<>();
    public MasageEntity() {
    }

    public MasageEntity(String stext, Long string_speed, Long string_color_type, Long string_color, String string_timing_type, String string_timing, Long showed) {
        this.stext = stext;
        this.string_speed = string_speed;
        this.string_color_type = string_color_type;
        this.string_color = string_color;
        this.string_timing_type = string_timing_type;
        this.string_timing = string_timing;
        this.showed = showed;
    }

    public Long getShowed() {
        return showed;
    }

    public void setShowed(Long showed) {
        this.showed = showed;
    }
    // getters and setters
    public void addString(RstringsEntity rstringsEntity) {
        this.strings.add(rstringsEntity);
        rstringsEntity.getMasagess().add(this);
    }

    public void removeStrings(long stringId) {
        RstringsEntity rstringsEntity = this.strings.stream().filter(t -> t.getId() == stringId).findFirst().orElse(null);
        if (rstringsEntity != null) this.strings.remove(rstringsEntity);
        rstringsEntity.getMasagess().remove(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStext() {
        return stext;
    }

    public void setStext(String string_text) {
        this.stext = string_text;
    }

    public Long getString_speed() {
        return string_speed;
    }

    public void setString_speed(Long string_speed) {
        this.string_speed = string_speed;
    }

    public Long getString_color_type() {
        return string_color_type;
    }

    public void setString_color_type(Long string_color_type) {
        this.string_color_type = string_color_type;
    }

    public Long getString_color() {
        return string_color;
    }

    public void setString_color(Long string_color) {
        this.string_color = string_color;
    }

    public String getString_timing_type() {
        return string_timing_type;
    }

    public void setString_timing_type(String string_timing_type) {
        this.string_timing_type = string_timing_type;
    }

    public String getString_timing() {
        return string_timing;
    }

    public void setString_timing(String string_timing) {
        this.string_timing = string_timing;
    }

    public Set<RstringsEntity> getMassages() {
        return strings;
    }
    public void setTutorials(Set<RstringsEntity> strings) {
        this.strings = strings;
    }
}
