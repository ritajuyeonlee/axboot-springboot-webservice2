package edu.axboot.controllers.dto.pms.sales;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class SalesResponseDto {
    private String rsvDt;
    private long count=0;
    private BigDecimal salePrc = BigDecimal.ZERO;
    private BigDecimal svcPrc= BigDecimal.ZERO;

    public BigDecimal getTotalPrc() {
        if(this.salePrc==null) this.salePrc = BigDecimal.ZERO;
        if(this.svcPrc==null) this.svcPrc = BigDecimal.ZERO;
        return this.salePrc.add(this.svcPrc);
    }

    public void setRsvDt(String rsvDt){
        this.rsvDt = rsvDt;
    }

    public void add(SalesResponseDto dto){
        if(dto==null) return ;
        this.count += dto.getCount();
        if(dto.getSalePrc()!=null) this.salePrc=this.salePrc.add(dto.getSalePrc());
        if(dto.getSvcPrc()!=null) this.svcPrc=this.svcPrc.add(dto.getSvcPrc());
    }
}
