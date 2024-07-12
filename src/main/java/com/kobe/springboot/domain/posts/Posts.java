package com.kobe.springboot.domain.posts;

import com.kobe.springboot.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 클래스 내 모든 필드의 Getter 메소드를 자동생성
@Getter

// 기본 생성자 자동 추가
// public Posts(){}와 같은 효과
@NoArgsConstructor

// 테이블과 링크될 클래스임을 나타냅니다.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭합니다.
	// ex) SalesManager -> sales_manager table
@Entity
public class Posts extends BaseTimeEntity {
	// 해당 테이블의 PK 필드를 나타냅니다.
	@Id

	// PK의 생성 규칙을 나타냅니다.
	// 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됩니다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 테이블 칼럼을 나태내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다.
	// 사용하는 이유는, 기본값 외에 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex: title), 타입을 TEXT로 변경하고 싶거나(ex: content)등의 경우에 사용됩니다.
	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	private String author;

	@Builder
	public Posts(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
