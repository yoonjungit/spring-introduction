package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>() ;
    private static long sequence = 0L ;
    @Override //@ :어노테이션. 주석이지만, 문법적으로 맞는지 어느정도 오류를 잡아주는 역할을 함
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id)); //ofNullable : null값 허용
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
    .filter(member -> member.getName().equals(name))
    .findAny();
    }
    public void clearStore() {  //메소드마다 저장소 지우기
    store.clear();
    }
}
