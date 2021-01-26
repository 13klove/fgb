package friend.group.bank.obj.job.sendPaydaySmsJob.reader;

import friend.group.bank.obj.domain.member.entity.Member;
import friend.group.bank.obj.domain.member.repository.MemberJpaRepository;
import friend.group.bank.obj.domain.member.status.PaydayStauts;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SendPaydaySmsListReader implements ItemReader<Member> {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Integer date = LocalDate.now().getDayOfMonth();

        List<Member> members = memberJpaRepository.findAll().stream()
                .filter(a->{
                    if(a.getPaydayStauts().equals(PaydayStauts.FIX)){
                        return getPaydayFix(a.getPayday())==date;
                    }else if(a.getPaydayStauts().equals(PaydayStauts.LAST)){
                        return getPaydayLast()==date;
                    }else{
                        return false;
                    }
                    }).collect(Collectors.toList());
        return !members.isEmpty()?members.remove(0):null;
    }

    public Integer getPaydayLast(){
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        Integer lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        while(true){
            int weekDay = getDays(calendar, year, month, lastDay);
            if(weekDay==1 || weekDay==7) lastDay-=1;
            else break;
        }

        return lastDay;
    }

    public Integer getPaydayFix(Integer payday){
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        Integer lastDay = payday;

        while(true){
            int weekDay = getDays(calendar, year, month, lastDay);
            if(weekDay==1 || weekDay==7) lastDay-=1;
            else break;
        }

        return lastDay;
    }

    public int getDays(Calendar calendar, int year, int month, Integer day){
        calendar.set(year, month, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
