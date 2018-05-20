<%--
  Created by IntelliJ IDEA.
  User: hoi
  Date: 5/4/2018
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="authentication_content"/>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<div id="notifications-wrapper">
    <section class="notifications">
        <section class="content">
            <div class="notificationsSlides">
                <h3>1. Conduct Research on the Employer, Hiring Manager, and Job Opportunity</h3>
                <p>Success in a job interview starts with a solid foundation of knowledge on the jobseeker’s part. You
                    should understand the employer, the requirements of the job, and the background of the person (or
                    people) interviewing you. The more research you conduct, the more you’ll understand the employer,
                    and
                    the better you’ll be able to answer interview questions (as well as ask insightful questions see
                    #8).
                    Scour the organization’s website and other published materials, search engines, research tools, and
                    ask
                    questions about the <a href="https://company.livecareer.com">company in your network</a> of
                    contacts.
                </p>
            </div>
            <div class="notificationsSlides">
                <h3>2. Review Common Interview Questions and Prepare Your Responses</h3>
                <p>Another key to <a href="https://www.myperfectresume.com/how-to/career-resources/interview/">interview
                    success</a> is preparing responses to <a href="https://www.livecareer.com/interview-questions">expected
                    interview questions</a>. First, ask the hiring manager as to the type of interview to expect. Will
                    it be
                    one-on-one or in a group? Will it be with one person, or will you meet several members of the
                    organization? Your goal is to try to determine what you’ll be asked and to compose detailed yet
                    concise
                    responses that focus on specific examples and accomplishments.</p>
                <p>A good tool for remembering your responses is to put them into a story form that you can tell in the
                    interview. No need to memorize responses (in fact, it’s best not to), but do develop talking points.
                    There are excellent <a href="https://www.livecareer.com/">tools</a> available to help you with
                    interview
                    questions and responses. Also, consider using the <a
                            href="https://www.livecareer.com/career/advice/interview/star-interviewing">STAR
                        Interviewing
                        Technique</a>.</p>
            </div>
            <div class="notificationsSlides">
                <h3>3. Dress for Success</h3>
                <p>Plan out a wardrobe that fits the organization and its culture, striving for the most professional
                    appearance you can accomplish. Remember that it’s always better to be overdressed than under and to
                    wear clothing that fits and is clean and pressed. Keep accessories and jewelry to a minimum. Try not
                    to
                    smoke or eat right before the interview and if possible, brush your teeth or use mouthwash.</p>
            </div>
            <div class="notificationsSlides">
                <h3>4. Arrive on Time, Relaxed and Prepared for the Interview</h3>
                <p>There is no excuse ever for arriving late to an interview. Short of a disaster, strive to arrive
                    about 15
                    minutes before your scheduled interview to complete additional paperwork and allow yourself time to
                    get
                    settled. Arriving a bit early is also a chance to observe the dynamics of the workplace.</p>
                <p>The day before the interview, pack up extra copies of your resume or CV and reference list. If you
                    have a
                    portfolio or samples of your work, bring those along too. Finally, remember to pack several pens and
                    a
                    pad of paper to jot notes. Finally, as you get to the offices, shut off your cell phone. (And if you
                    were chewing gum, get rid of it.)</p>
            </div>
            <div class="notificationsSlides">
                <h3>5. Make Good First Impressions</h3>
                <p>A cardinal rule of interviewing is to be polite and offer warm greetings to everyone you meet from
                    the
                    parking attendant to the receptionist to the hiring manager. Employers often are curious how job
                    applicants treat staff members and your job offer could easily be derailed if you’re rude or
                    arrogant
                    to any of the staff. When it’s time for the interview, keep in mind that first impressions the
                    impression interviewers get in the first few seconds of meeting you can make or break an
                    interview.</p>
                <blockquote class="tip-quote"><p>Make a strong first impression by dressing well (see #3), arriving
                    early
                    (see #4), and when greeting your interviewer, stand, smile, make eye contact, and offer a firm&#147;
                    but
                    not bone-crushing&#147; handshake.</p></blockquote>
                <p>Remember that having a positive attitude and expressing enthusiasm for the job and employer are vital
                    in
                    the initial stages of the interview; studies show that hiring managers make critical decisions about
                    job
                    applicants in the first 20 minutes of the interview.</p>
            </div>
            <div class="notificationsSlides">
                <h3>6. Be Authentic, Upbeat, Focused, Confident, Candid, and Concise</h3>
                <p>Once the interview starts, the key to success is the quality and delivery of your responses. Your
                    goal
                    should always be authenticity, responding truthfully to interview questions. At the same time, your
                    goal
                    is to get to the next step, so you’ll want to provide focused responses that showcase your skills,
                    experience, and fit with the job and the employer. Provide solid examples of solutions and
                    accomplishments but keep your responses short and to the point.</p>
                <p>By preparing responses to common interview questions (see #2), you’ll ideally avoid long, rambling
                    responses that bore interviewers. Always attempt to keep your interview responses short and to the
                    point. Finally, no matter how much an interviewer might bait you, never badmouth a previous
                    employer,
                    boss, or co-worker. The interview is about you and making your case that you are the ideal
                    candidate
                    for the job.</p>
            </div>
            <div class="notificationsSlides">
                <h3>7. Remember the Importance of Body Language</h3>
                <p>While the content of your interview responses is paramount, poor body language can be a distraction
                    at
                    best or a reason not to hire you at worst. Effective forms of body language include smiling, eye
                    contact, solid posture, active listening, and nodding. Detrimental forms of body language include
                    slouching, looking off in the distance, playing with a pen, fidgeting in a chair, brushing back your
                    hair, touching your face, chewing gum, or mumbling.</p>
            </div>
            <div class="notificationsSlides">
                <h3>8. Ask Insightful Questions</h3>
                <p>Studies continually show that employers make a judgment about an applicant’s interest in the job by
                    whether or not the interviewee asks questions. Thus, even if the hiring manager was thorough in his
                    or
                    her discussions about the job opening and what is expected, you must ask a few questions. This shows
                    that you have done your research and that you are curious. The smart jobseeker prepares questions to
                    ask
                    days before the interview, adding any additional queries that might arise from the interview.</p>
            </div>
            <div class="notificationsSlides">
                <h3>9. Sell Yourself and then Close the Deal</h3>
                <p>The most qualified applicant is not always the one who is hired; the winning candidate is often the
                    jobseeker who does the best job responding to interview questions and showcasing his or her fit with
                    the
                    job, department, and organization. Some liken the job interview to a sales call. You are the
                    salesperson and the product you are selling to the employer is your ability to fill the
                    organization’s
                    needs, solve its problems, propel its success.</p>
                <p>Finally, as the interview winds down, ask about the next steps in the process and the timetable in
                    which
                    the employer expects to use to make a decision about the position.</p>
            </div>
            <div class="notificationsSlides">
                <h3>10. Thank Interviewer(s) in Person, by Email, or Postal Mail</h3>
                <p>Common courtesy and politeness go far in interviewing; thus, the importance of thanking each person
                    who
                    interviews you should come as no surprise. Start the process while at the interview, thanking each
                    person who interviewed you before you leave. Writing thank-you emails or notes shortly after the
                    interview will not get you the job offer, but doing so will certainly give you an edge over any of
                    the
                    other finalists who didn’t bother to send thank-you notes.</p>
            </div>

        </section>
        <button class="close-sign" onclick="document.getElementById('notifications-wrapper').remove();">&#120</button>
        <section class="content controls">
            <div class="tips-disabler">
                <input type="checkbox" id="disable-tips"
                       onclick="localStorage.setItem('tipsAreDisabled',this.checked?'true':'false');">
                <label for="disable-tips">Disable Tips</label>
            </div>
            <div class="slider-wrapper">
                <button onclick="plusDivs(-1)">&#10094;</button>
                <div id="slider-selector">
                </div>
                <button onclick="plusDivs(+1)">&#10095;</button>
            </div>
        </section>
    </section>
    <script>
        showDots();
        showDivs(slideIndex);
    </script>
</div>
</body>
</html>
